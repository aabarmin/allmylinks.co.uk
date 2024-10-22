import { FormatAlignCenter, FormatAlignLeft, FormatAlignRight } from "@mui/icons-material";
import { FormControl, FormLabel, IconButton, Input, Option, Select, Stack, ToggleButtonGroup, Typography, TypographySystem } from "@mui/joy";
import { BlockUpdatePropsRequest } from "../hooks/block/BlockUpdatePropsRequest";
import { useAppState } from "../hooks/StateProvider";
import { Block, BlockType } from "../model/Block";

export enum HeaderLevel {
  H1 = 'H1',
  H2 = 'H2',
  H3 = 'H3'
}

export enum HeaderAlignment {
  CENTER = 'CENTER',
  LEFT = 'LEFT',
  RIGHT = 'RIGHT'
}

export class HeaderBlockProps {
  level: HeaderLevel = HeaderLevel.H1;
  alignment: HeaderAlignment = HeaderAlignment.LEFT;
  text: string = 'Put your text here';
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

export function HeaderBlockProperties(block: HeaderBlock) {
  const props = block.props
  const { state, dispatch } = useAppState();

  const changeLevel = (event: React.SyntheticEvent | null, newValue: string | null) => {
    if (!newValue) {
      return
    }
    dispatch(new BlockUpdatePropsRequest(
      block,
      (current: HeaderBlockProps): HeaderBlockProps => {
        const updated = new HeaderBlockProps()
        updated.alignment = current.alignment
        updated.text = current.text
        updated.level = HeaderLevel[newValue as keyof typeof HeaderLevel]
        return updated;
      }
    ))
  }

  const changeAlignment = (event: any, newValue: string | null) => {
    if (!newValue) {
      return
    }
    dispatch(new BlockUpdatePropsRequest(
      block,
      (current: HeaderBlockProps): HeaderBlockProps => {
        const updated = new HeaderBlockProps()
        updated.alignment = HeaderAlignment[newValue as keyof typeof HeaderAlignment]
        updated.text = current.text
        updated.level = current.level
        return updated;
      }
    ))
  }

  const changeText = (newValue: string) => {
    dispatch(new BlockUpdatePropsRequest(
      block,
      (current: HeaderBlockProps): HeaderBlockProps => {
        const updated = new HeaderBlockProps()
        updated.alignment = current.alignment
        updated.text = newValue
        updated.level = current.level
        return updated;
      }
    ))
  }

  return (
    <Stack spacing={2}>
      <FormControl>
        <FormLabel>Level:</FormLabel>
        <Select placeholder="Header level" value={props.level} onChange={changeLevel}>
          <Option value={HeaderLevel.H1}>Level 1</Option>
          <Option value={HeaderLevel.H2}>Level 2</Option>
          <Option value={HeaderLevel.H3}>Level 3</Option>
        </Select>
      </FormControl>

      <FormControl>
        <FormLabel>Alignment:</FormLabel>
        <ToggleButtonGroup value={props.alignment} onChange={changeAlignment}>
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
        <Input placeholder="Text" value={props.text} onChange={(e) => changeText(e.target.value)} />
      </FormControl>
    </Stack>
  );
}

export function HeaderBlockComponent(props: HeaderBlockProps) {
  const level = props.level.toLowerCase() as keyof TypographySystem
  const align = props.alignment.toLowerCase() as string
  return (
    <Typography level={level} align={align}>{props.text}</Typography>
  );
}