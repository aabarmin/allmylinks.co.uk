import { BlockUpdatePropsRequest } from "@/app/hooks/block/BlockUpdatePropsRequest";
import { useAppState } from "@/app/hooks/StateProvider";
import { updateBlock } from "@/lib/client/blockActions";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import LinearProgress from "@mui/material/LinearProgress";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import { useEffect, useState } from "react";
import { z } from "zod";
import { BlockManagement } from "../components/BlockManagement";
import { LinkButtonBlock } from "./LinkButtonBlock";

type FormState = {
  errors?: {
    text?: string[],
    link?: string[]
  }
} | undefined;

export function LinkButtonBlockProperties(block: LinkButtonBlock) {
  const validatinonScheme = z.object({
    text: z.string().min(3),
    link: z.string().url()
  });
  const [validation, setValidation] = useState<FormState>();
  const { state, dispatch } = useAppState();
  const [isLoading, setLoading] = useState(false);
  const [form, setFormData] = useState(({
    text: block.props.text,
    link: block.props.link
  }));
  const saveBlock = () => {
    const props = {
      text: form['text'],
      link: form['link']
    };
    setLoading(true);

    const validationResult = validatinonScheme.safeParse(props);
    if (!validationResult.success) {
      setLoading(false);
      setValidation({
        errors: validationResult.error.flatten().fieldErrors
      });
      return;
    }
    setValidation(undefined);

    updateBlock(block, props).then(() => {
      setLoading(false);
      dispatch(new BlockUpdatePropsRequest(
        block,
        () => props
      ))
    })
  }
  const resetBlock = () => {
    setFormData({
      text: block.props.text,
      link: block.props.link
    });
  }
  const handleInput = (field: string, value: string) => {
    setFormData((prev) => ({
      ...prev,
      [field]: value
    }));
  };
  useEffect(() => { resetBlock() }, [state]);

  return (
    <Stack spacing={2}>
      <Box sx={{
        display: 'flex',
        gap: 2,
        paddingBottom: 2
      }}>
        <Button
          variant="outlined"
          disabled={isLoading}
          onClick={resetBlock}>
          Cancel
        </Button>
        <Button
          variant="contained"
          onClick={saveBlock}
          disabled={isLoading}>
          Save
        </Button>
        <BlockManagement
          block={block}
          setLoading={setLoading}
          disabled={isLoading} />
      </Box>

      {isLoading && <LinearProgress />}

      <Stack spacing={2}>
        <TextField
          fullWidth
          disabled={isLoading}
          error={!!validation?.errors?.text}
          helperText={validation?.errors?.text?.join(', ')}
          value={form['text']}
          onChange={(e) => handleInput('text', e.target.value)}
          label="Text on the button"
          name="text"
        />

        <TextField
          fullWidth
          disabled={isLoading}
          error={!!validation?.errors?.link}
          helperText={validation?.errors?.link?.join(', ')}
          value={form['link']}
          onChange={(e) => handleInput('link', e.target.value)}
          label="URL of the link"
          name="link"
        />
      </Stack>
    </Stack>
  );
}