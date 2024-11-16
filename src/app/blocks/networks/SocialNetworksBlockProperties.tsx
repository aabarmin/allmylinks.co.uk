import { BlockUpdatePropsRequest } from "@/app/hooks/block/BlockUpdatePropsRequest";
import { useAppState } from "@/app/hooks/StateProvider";
import { updateBlock } from "@/lib/client/blockActions";
import { Delete } from "@mui/icons-material";
import { FormHelperText } from "@mui/material";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import LinearProgress from "@mui/material/LinearProgress";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import { useEffect, useState } from "react";
import { z } from "zod";
import { BlockManagement } from "../components/BlockManagement";
import { SocialNetwork, SocialNetworksBlock, SocialNetworksBlockProps } from "./SocialNetworksBlock";

function arrayCopyAndAdd<T>(arr: T[], item: T): T[] {
  return [...arr, item];
}

type LineValidationResult = {
  valid: boolean,
  errors?: {
    icon?: string[],
    link?: string[]
  }
};

type ValidationResult = {
  valid: boolean,
  errors?: string[],
  lines?: LineValidationResult[]
}

type FormModel = {
  networks: SocialNetwork[]
};

const formLineScheme = z.object({
  icon: z.string().min(3, 'Network should be selected'),
  link: z.string().min(3, 'Link should be provided')
});

function validate(form: FormModel): ValidationResult {
  // checking if there are more than zero networks
  if (form.networks.length == 0) {
    return {
      valid: false,
      errors: ['At least one network is required']
    }
  }
  // checking each line of the form
  const lines = form.networks.map((line) => {
    const validationResult = formLineScheme.safeParse(line);
    if (!validationResult.success) {
      return {
        valid: false,
        errors: validationResult.error.flatten().fieldErrors
      }
    }
    return {
      valid: true
    }
  });

  return {
    valid: lines.every(l => l.valid),
    lines: lines
  };
}

export function SocialNetworksBlockProperties(block: SocialNetworksBlock) {
  const { dispatch } = useAppState();
  const [validation, setValidation] = useState<ValidationResult>();
  const [form, setFormData] = useState<FormModel>({
    networks: []
  });
  useEffect(() => {
    setFormData((f) => ({
      ...f,
      ['networks']: block.props.networks
    }))
  }, [block]);

  const addSocialNetwork = () => {
    const network = new SocialNetwork(form['networks'].length)

    setFormData((f) => ({
      ...f,
      ['networks']: arrayCopyAndAdd(f['networks'], network)
    }));
  }

  const resetForm = () => {
    setFormData((f) => ({
      ...f,
      ['networks']: block.props.networks
    }))
  }

  const [isLoading, setLoading] = useState(false);
  const saveForm = () => {
    const validationResult = validate(form);
    setValidation(validationResult);
    if (!validationResult.valid) {
      return;
    }
    setValidation(undefined);

    const props = new SocialNetworksBlockProps()
    props.networks = form['networks']
    setLoading(true);

    updateBlock(block, props).then(() => {
      setLoading(false);
      dispatch(new BlockUpdatePropsRequest(
        block,
        () => props
      ))
    });
  }

  const deleteSocialNetwork = (order: number) => {
    setFormData((f) => ({
      ...f,
      ['networks']: f['networks'].filter(n => n.order != order)
    }))
  }

  const updateSocialNetwork = (order: number, field: string, value: string | null) => {
    setFormData((f) => ({
      ...f,
      ['networks']: f['networks'].map(n => {
        if (n.order != order) {
          return n;
        }
        const nw = new SocialNetwork(n.order)
        nw.icon = n.icon;
        nw.link = n.link;
        if (field == 'icon') {
          nw.icon = value as string;
        } else if (field == 'link') {
          nw.link = value as string;
        } else {
          console.error('Unknown field ' + field)
        }
        return nw;
      })
    }))
  }

  return (
    <Stack spacing={2}>
      <Box sx={{
        display: 'flex',
        gap: 2
      }}>
        <Button
          disabled={isLoading}
          onClick={resetForm}
          variant="outlined">
          Cancel
        </Button>
        <Button
          disabled={isLoading}
          variant="contained"
          onClick={saveForm}>
          Save
        </Button>
        <BlockManagement
          block={block}
          setLoading={setLoading}
          disabled={isLoading} />
      </Box>

      {isLoading && <LinearProgress />}

      <Button
        fullWidth
        disabled={isLoading}
        variant="contained"
        onClick={addSocialNetwork}>Add a link</Button>

      <Stack spacing={2}>
        <FormHelperText>
          {validation?.errors?.join(', ')}
        </FormHelperText>
        {form['networks'].map(sn => {
          return (
            <Stack key={sn.order}>
              <Box
                key={sn.order}
                sx={{
                  gap: 2,
                  display: 'flex',
                  flexDirection: 'row'
                }}>
                <Select
                  value={sn.icon}
                  disabled={isLoading}
                  onChange={(e) => updateSocialNetwork(sn.order, 'icon', e.target.value)}>
                  <MenuItem value="twitter">
                    Twitter
                  </MenuItem>
                  <MenuItem value="instagram">
                    Instagram
                  </MenuItem>
                  <MenuItem value="facebook">
                    Facebook
                  </MenuItem>
                </Select>
                <TextField
                  disabled={isLoading}
                  value={sn.link}
                  onChange={(e) => updateSocialNetwork(sn.order, 'link', e.target.value)}
                  placeholder="Add URL" />
                <IconButton
                  disabled={isLoading}
                  onClick={() => deleteSocialNetwork(sn.order)}>
                  <Delete />
                </IconButton>
              </Box>
              <FormHelperText>
                {validation?.lines?.[sn.order]?.errors?.icon?.join(', ')}
                {validation?.lines?.[sn.order]?.errors?.link?.join(', ')}
              </FormHelperText>
            </Stack>
          )
        })}
      </Stack>
    </Stack>
  )
}