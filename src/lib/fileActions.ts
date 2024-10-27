import { FileUploadResponse } from "@/app/(api)/api/files/FileUploadResponse";

export async function uploadFile(file: File): Promise<FileUploadResponse> {
  const formData = new FormData();
  formData.append('file', file);
  const response = await fetch('/api/files', {
    method: 'POST',
    body: formData
  });
  return await response.json();
}