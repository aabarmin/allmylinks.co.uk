import { BlockUpdatePropsRequest } from "@/app/hooks/block/BlockUpdatePropsRequest";
import { useAppState } from "@/app/hooks/StateProvider";
import { updateBlock } from "@/lib/client/blockActions";
import { uploadFile } from "@/lib/client/fileActions";
import { Box, Button, LinearProgress, Stack } from "@mui/joy";
import Image from "next/image";
import { BaseSyntheticEvent, useEffect, useRef, useState } from "react";
import { AvatarBlock, AvatarBlockProps, DEFAULT_AVATAR } from "./AvatarBlock";

export function AvatarBlockProperties(block: AvatarBlock) {
  const { dispatch } = useAppState();
  const [isLoading, setLoading] = useState<boolean>(false);
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
    const props = new AvatarBlockProps()
    props.imageUrl = form['imageUrl']
    setLoading(true);

    updateBlock(block, props).then(() => {
      setLoading(false);
      dispatch(new BlockUpdatePropsRequest(
        block,
        () => props
      ))
    });
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
    setLoading(true);
    uploadFile(file).then(uploaded => {
      setLoading(false);
      setFormData((f) => ({
        ...f,
        ['imageUrl']: uploaded.filePath
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

      {isLoading && <LinearProgress />}

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