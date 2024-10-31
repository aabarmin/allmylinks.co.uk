import { getDbClient } from "@/lib/server/dbClient";
import { getCurrentUserId } from "@/lib/server/userActions";
import fs from "fs";
import path from "path";
import { FileUploadResponse } from "./FileUploadResponse";

export async function POST(
  request: Request
) {
  const formData = await request.formData()
  const file = formData.get('file') as File
  const currentUser = await getCurrentUserId();

  if (!currentUser) {
    return new Response('Unauthorized', { status: 401 })
  }
  if (!file) {
    return new Response('No file found in request', { status: 400 })
  }

  const buffer = Buffer.from(await file.arrayBuffer())
  const filename = file.name.replaceAll(" ", "_");

  const uploadsPath = path.join(process.cwd(), 'public', 'uploads');
  if (!fs.existsSync(uploadsPath)) {
    fs.mkdirSync(uploadsPath, { recursive: true })
  }

  let targetFileName = filename;
  while (fs.existsSync(path.join(uploadsPath, targetFileName))) {
    targetFileName = `${Date.now()}_${filename}`;
  }

  fs.writeFileSync(path.join(uploadsPath, targetFileName), buffer);

  const created = await getDbClient().uploadedFile.create({
    data: {
      authorId: currentUser,
      filePath: path.join('uploads', targetFileName),
      fileName: targetFileName
    }
  })
  const response: FileUploadResponse = {
    filePath: '/' + created.filePath,
  }

  return Response.json(response)
}