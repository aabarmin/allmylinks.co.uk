/*
  Warnings:

  - Added the required column `file_name` to the `uploaded_files` table without a default value. This is not possible if the table is not empty.

*/
-- AlterTable
ALTER TABLE "uploaded_files" ADD COLUMN     "file_name" TEXT NOT NULL;
