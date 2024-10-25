'use client';

import { Box, Button, Stack } from "@mui/joy";
import Image from "next/image";
import { BaseSyntheticEvent, useEffect, useRef, useState } from "react";
import { BlockUpdatePropsRequest } from "../hooks/block/BlockUpdatePropsRequest";
import { useAppState } from "../hooks/StateProvider";
import { Block, BlockType } from "../model/Block";

export const DEFAULT_AVATAR = "/avatar_placeholder.jpeg";
export class AvatarBlockProps {
  imageUrl: string = DEFAULT_AVATAR;
}

export class AvatarBlock implements Block<AvatarBlockProps> {
  id: number;
  type: BlockType;
  order: number;
  props: AvatarBlockProps;

  constructor(id: number, order: number) {
    this.id = id;
    this.order = order;
    this.type = BlockType.BLOCK_AVATAR;
    this.props = new AvatarBlockProps();
  }
}

export class FileDetails {
  fileName: string = '';
  fileUrl: string = '';
}

function uploadFile(file: File): Promise<FileDetails> {
  return new Promise(resolve => {
    resolve({
      fileName: file.name,
      fileUrl: '/avatar_updated.jpeg'
    })
  });
}

export function AvatarBlockProperties(block: AvatarBlock) {
  const { dispatch } = useAppState();
  const fileUploadRef = useRef<HTMLInputElement>(null);
  const [form, setFormData] = useState({
    imageUrl: DEFAULT_AVATAR
  });
  useEffect(() => {
    setFormData((f) => ({
      ...f,
      ['imageUrl']: block.props.imageUrl
    }))
  }, [block]);
  const saveChanges = () => {
    dispatch(new BlockUpdatePropsRequest(
      block,
      () => {
        const props = new AvatarBlockProps()
        props.imageUrl = form['imageUrl']
        return props
      }
    ))
  };
  const resetAvatar = () => {
    setFormData((f) => ({
      ...f,
      ['imageUrl']: DEFAULT_AVATAR
    }))
  }
  const selectAvatar = () => {
    fileUploadRef.current?.click()
  }
  const uploadAndProcessAvatar = (e: BaseSyntheticEvent) => {
    const files = e.target.files as FileList
    const file = files[0]
    if (file == null || file == undefined) {
      return
    }
    // no rich image editor for now, just because it is outside of the MVP
    // and quite complicated. 
    uploadFile(file).then(uploaded => {
      setFormData((f) => ({
        ...f,
        ['imageUrl']: uploaded.fileUrl
      }))
    })
  }

  return (
    <Stack spacing={2}>
      <Box sx={{
        display: 'flex',
        gap: 2
      }}>
        <Button variant="soft">Cancel</Button>
        <Button onClick={saveChanges}>Save</Button>
      </Box>
      <Box sx={{
        gap: 2,
        display: 'flex',
        flexDirection: 'column'
      }}>
        <Box sx={{
          display: 'flex',
          justifyContent: 'center'
        }}>
          <Image
            alt="Default avatar"
            width={110}
            height={110}
            style={{
              border: '2px solid black',
              borderRadius: '50%'
            }}
            src={form['imageUrl']} />
        </Box>
        <Box sx={{
          display: 'flex',
          justifyContent: 'center',
          gap: 2
        }}>
          <Button onClick={resetAvatar}>Remove avatar</Button>
          <Button onClick={selectAvatar}>Upload new</Button>
        </Box>
        <input
          type="file"
          onChange={uploadAndProcessAvatar}
          ref={fileUploadRef}
          style={{ display: 'none' }} />
      </Box>
    </Stack>
  );
}

export default function AvatarBlockComponent(props: AvatarBlockProps) {
  return (
    <Box sx={{
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      height: '200px'
    }}>
      <Image
        alt="Default avatar"
        width={110}
        height={110}
        style={{
          border: '2px solid black',
          borderRadius: '50%'
        }}
        src={props.imageUrl} />
    </Box>
  )
}