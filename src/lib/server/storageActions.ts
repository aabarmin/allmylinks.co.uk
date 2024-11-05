import { UploadedFile, User } from "@prisma/client";
import { put } from "@vercel/blob";
import { getDbClient } from "./dbClient";

async function isFileExists(fullFilePath: string): Promise<boolean> {
  const db = getDbClient();
  const exists = await db.uploadedFile.count({
    where: {
      filePath: fullFilePath
    }
  })

  return exists > 0;
}

export async function uploadFile(file: File, user: User): Promise<UploadedFile> {
  const filename = file.name.replaceAll(" ", "_");
  const env = process.env.APP_ENVIRONMENT || 'dev';

  let fullFilePath = `${env}/uploads/user_${user.id}/${Date.now()}_${filename}`;
  let exists = await isFileExists(fullFilePath);
  while (exists) {
    fullFilePath = `${env}/uploads/user_${user.id}/${Date.now()}_${filename}`;
    exists = await isFileExists(fullFilePath);
  }

  const response = await put(fullFilePath, file, {
    access: 'public'
  });

  const uploadedFile = await getDbClient().uploadedFile.create({
    data: {
      authorId: user.id,
      filePath: response.pathname,
      fileName: filename,
      publicUrl: response.downloadUrl
    }
  });

  return Promise.resolve(uploadedFile);
}