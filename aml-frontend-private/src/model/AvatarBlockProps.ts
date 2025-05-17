import type { BlockProps } from "./BlockProps";
import type { FileResponse } from "./FileId";

export interface AvatarBlockProps extends BlockProps {
  avatar: FileResponse;
  background?: FileResponse;
  showShareButton: boolean;
}