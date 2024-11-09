import { BlockUpdatePropsRequest } from "@/app/hooks/block/BlockUpdatePropsRequest";
import { useAppState } from "@/app/hooks/StateProvider";
import { updateBlock } from "@/lib/client/blockActions";
import { Delete } from "@mui/icons-material";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import LinearProgress from "@mui/material/LinearProgress";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import { useEffect, useState } from "react";
import { BlockManagement } from "../components/BlockManagement";
import { SocialNetwork, SocialNetworksBlock, SocialNetworksBlockProps } from "./SocialNetworksBlock";

function arrayCopyAndAdd<T>(arr: T[], item: T): T[] {
  return [...arr, item];
}

export function SocialNetworksBlockProperties(block: SocialNetworksBlock) {
  const { dispatch } = useAppState();
  const [form, setFormData] = useState({
    networks: [] as SocialNetwork[]
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

      <Box sx={{
        gap: 2,
        display: 'flex',
        flexDirection: 'column'
      }}>
        {form['networks'].map(sn => {
          return (
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
          )
        })}
      </Box>
    </Stack>
  )
}