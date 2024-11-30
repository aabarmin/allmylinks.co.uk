import { uploadFile } from "@/lib/server/storageActions";
import { getCurrentUser } from "@/lib/server/userActions";
import { FileUploadResponse } from "./FileUploadResponse";

export async function POST(
  request: Request
) {
  const formData = await request.formData()
  const file = formData.get('file') as File
  const currentUser = await getCurrentUser();

  if (!currentUser) {
    return new Response('Unauthorized', { status: 401 })
  }
  if (!file) {
    return new Response('No file found in request', { status: 400 })
  }

  const uploadedFile = await uploadFile(file, currentUser)
  const response: FileUploadResponse = {
    filePath: uploadedFile.publicUrl,
    fileId: uploadedFile.id,
  }

  return Response.json(response);
}