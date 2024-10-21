import { FormatAlignCenter, FormatAlignLeft, FormatAlignRight } from "@mui/icons-material";
import { FormControl, FormLabel, IconButton, Input, Option, Select, Stack, ToggleButtonGroup } from "@mui/joy";
import { Block, BlockType } from "../model/Block";

export const enum HeaderLevel {
  H1 = 'H1',
  H2 = 'H2',
  H3 = 'H3'
}

export const enum HeaderAlignment {
  CENTER = 'CENTER',
  LEFT = 'LEFT',
  RIGHT = 'RIGHT'
}

export class HeaderBlockProps {
  level: HeaderLevel = HeaderLevel.H1;
  alignment: HeaderAlignment = HeaderAlignment.CENTER;
  text: string = '';
}

export class HeaderBlock implements Block<HeaderBlockProps> {
  id: number;
  type: BlockType;
  order: number;
  props: HeaderBlockProps;

  constructor(id: number, order: number) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_HEADER;
    this.props = new HeaderBlockProps();
  }
}

export function HeaderBlockProperties(props: HeaderBlockProps) {
  console.log(props)

  return (
    <Stack spacing={2}>
      <FormControl>
        <FormLabel>Level:</FormLabel>
        <Select placeholder="Header level" value={props.level}>
          <Option value={HeaderLevel.H1}>Level 1</Option>
          <Option value={HeaderLevel.H2}>Level 2</Option>
          <Option value={HeaderLevel.H3}>Level 3</Option>
        </Select>
      </FormControl>

      <FormControl>
        <FormLabel>Alignment:</FormLabel>
        <ToggleButtonGroup value={props.alignment}>
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
        <Input placeholder="Text" value={props.text} />
      </FormControl>
    </Stack>
  );
}

export function HeaderBlockComponent(props: HeaderBlockProps) {
  return (
    <h1>Header is here: {props.text}</h1>
  );
}