'use client';

import { Avatar, Box, Button, FormControl, FormLabel, Input, Radio, RadioGroup, Stack } from "@mui/joy";
import { Block, BlockType } from "../model/Block";

export class BlockAvatarProps { }

export class AvatarBlock implements Block<BlockAvatarProps> {
  id: number;
  type: BlockType;
  order: number;
  props: BlockAvatarProps;

  constructor(id: number, order: number) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_AVATAR;
    this.props = new BlockAvatarProps();
  }
}

export function AvatarBlockProperties(block: AvatarBlock) {
  return (
    <Stack spacing={2}>
      <Box sx={{
        display: 'flex',
        gap: 2
      }}>
        <Button variant="soft">Cancel</Button>
        <Button>Save</Button>
      </Box>
      <FormControl>
        <FormLabel>Type:</FormLabel>
        <RadioGroup defaultValue={'text'}>
          <Radio value={'text'} label="Text" />
          <Radio value={'image'} label="Image" />
        </RadioGroup>
      </FormControl>
      <FormControl>
        <FormLabel>Text:</FormLabel>
        <Input placeholder="Initials" />
      </FormControl>
      <FormControl>
        <FormLabel>Image:</FormLabel>

      </FormControl>
    </Stack>
  );
}

export default function AvatarBlockComponent() {
  return (
    <Avatar>
      AB
    </Avatar>
  );
}