import { BlockUpdatePropsRequest } from "@/app/hooks/block/BlockUpdatePropsRequest";
import { useAppState } from "@/app/hooks/StateProvider";
import { updateBlock } from "@/lib/client/blockActions";
import { FormatAlignCenter, FormatAlignLeft, FormatAlignRight } from "@mui/icons-material";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import LinearProgress from "@mui/material/LinearProgress";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import ToggleButton from "@mui/material/ToggleButton";
import ToggleButtonGroup from "@mui/material/ToggleButtonGroup";
import Typography from "@mui/material/Typography";
import { useEffect, useState } from "react";
import { HeaderAlignment, HeaderBlock, HeaderBlockProps, HeaderLevel } from "./HeaderBlock";

export function HeaderBlockProperties(block: HeaderBlock) {
  const { state, dispatch } = useAppState();
  const [isLoading, setLoading] = useState(false);
  const [form, setFormData] = useState(({
    level: block.props.level,
    alignment: block.props.alignment,
    text: block.props.text
  }));
  const handleInput = (field: string, value: string | HeaderLevel | HeaderAlignment | null) => {
    setFormData((prev) => ({
      ...prev,
      [field]: value
    }));
  }
  const saveBlock = () => {
    const props = new HeaderBlockProps()
    props.alignment = HeaderAlignment[form['alignment'] as keyof typeof HeaderAlignment]
    props.level = HeaderLevel[form['level'] as keyof typeof HeaderLevel]
    props.text = form['text']
    setLoading(true);

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
      level: block.props.level,
      alignment: block.props.alignment,
      text: block.props.text
    })
  }
  useEffect(() => { resetBlock() }, [state]); // dirty hack but I don't care now

  return (
    <Stack spacing={2}>
      <Box sx={{
        display: 'flex',
        gap: 2
      }}
      >
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
      </Box>

      {isLoading && <LinearProgress />}

      <Typography>
        Alignment:
      </Typography>

      <ToggleButtonGroup
        exclusive
        onChange={(e, v) => handleInput('alignment', v)}
        disabled={isLoading}
        value={form['alignment']}>
        <ToggleButton value={HeaderAlignment.LEFT}>
          <FormatAlignLeft />
        </ToggleButton>
        <ToggleButton value={HeaderAlignment.CENTER}>
          <FormatAlignCenter />
        </ToggleButton>
        <ToggleButton value={HeaderAlignment.RIGHT}>
          <FormatAlignRight />
        </ToggleButton>
      </ToggleButtonGroup>

      <TextField
        disabled={isLoading}
        label="Text"
        onChange={(e) => handleInput('text', e.target.value)}
        value={form['text']}
      />

      {/* <FormControl>
        <FormLabel>Level:</FormLabel>
        <Select placeholder="Header level" value={form['level']} onChange={(e, v) => handleInput('level', v)}>
          <Option value={HeaderLevel.H1}>Level 1</Option>
          <Option value={HeaderLevel.H2}>Level 2</Option>
          <Option value={HeaderLevel.H3}>Level 3</Option>
        </Select>
      </FormControl>

      <FormControl>
        <FormLabel>Alignment:</FormLabel>
        <ToggleButtonGroup value={form['alignment']} onChange={(e, v) => handleInput('alignment', v)}>
          <IconButton value={HeaderAlignment.LEFT}>
            <FormatAlignLeft />
          </IconButton>
          <IconButton value={HeaderAlignment.CENTER}>
            <FormatAlignCenter />
          </IconButton>
          <IconButton value={HeaderAlignment.RIGHT}>
            <FormatAlignRight />
          </IconButton>
        </ToggleButtonGroup>
      </FormControl>

      <FormControl>
        <FormLabel>Text:</FormLabel>
        <Input
          placeholder="Text"
          value={form['text']}
          onChange={(e) => handleInput('text', e.target.value)} />
      </FormControl> */}
    </Stack>
  );
}