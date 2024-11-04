import { UploadedFile, User } from "@prisma/client";
import { getDbClient } from "./dbClient";
import { getSupabaseClient } from "./supabaseClient";

async function isFileExists(fullFilePath: string): Promise<boolean> {
  const db = getDbClient();
  const bucket = process.env.SUPABASE_BUCKET as string;
  const exists = await db.uploadedFile.count({
    where: {
      bucket: bucket,
      filePath: fullFilePath
    }
  })

  return exists > 0;
}

export async function uploadFile(file: File, user: User): Promise<UploadedFile> {
  const filename = file.name.replaceAll(" ", "_");

  const bucket = process.env.SUPABASE_BUCKET as string;
  const client = getSupabaseClient();

  let fullFilePath = `uploads/user_${user.id}/${filename}`;
  let exists = await isFileExists(fullFilePath);
  while (exists) {
    fullFilePath = `uploads/user_${user.id}/${Date.now()}_${filename}`;
    exists = await isFileExists(fullFilePath);
  }

  const response = await client.storage
    .from(bucket)
    .upload(fullFilePath, file, {
      cacheControl: '3600',
      upsert: false,
    })

  if (!response.data) {
    console.warn('Failed to upload file', response.error)
    throw new Error('Failed to upload file')
  }

  const publicUrl = await client.storage
    .from(bucket)
    .getPublicUrl(response.data.path);

  const uploadedFile = await getDbClient().uploadedFile.create({
    data: {
      authorId: user.id,
      filePath: fullFilePath,
      fileName: filename,
      publicUrl: publicUrl.data.publicUrl,
      bucket: bucket
    }
  });

  return Promise.resolve(uploadedFile);
}