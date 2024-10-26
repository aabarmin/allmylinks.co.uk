/*
  Warnings:

  - You are about to drop the column `createdAt` on the `pages` table. All the data in the column will be lost.
  - You are about to drop the column `isHome` on the `pages` table. All the data in the column will be lost.
  - You are about to drop the column `profileId` on the `pages` table. All the data in the column will be lost.
  - You are about to drop the column `updatedAt` on the `pages` table. All the data in the column will be lost.
  - You are about to drop the column `createdAt` on the `profiles` table. All the data in the column will be lost.
  - You are about to drop the column `updatedAt` on the `profiles` table. All the data in the column will be lost.
  - You are about to drop the column `userId` on the `profiles` table. All the data in the column will be lost.
  - Added the required column `profile_id` to the `pages` table without a default value. This is not possible if the table is not empty.
  - Added the required column `updated_at` to the `pages` table without a default value. This is not possible if the table is not empty.
  - Added the required column `updated_at` to the `profiles` table without a default value. This is not possible if the table is not empty.
  - Added the required column `user_id` to the `profiles` table without a default value. This is not possible if the table is not empty.

*/
-- DropForeignKey
ALTER TABLE "pages" DROP CONSTRAINT "pages_profileId_fkey";

-- AlterTable
ALTER TABLE "pages" DROP COLUMN "createdAt",
DROP COLUMN "isHome",
DROP COLUMN "profileId",
DROP COLUMN "updatedAt",
ADD COLUMN     "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN     "is_home" BOOLEAN NOT NULL DEFAULT false,
ADD COLUMN     "profile_id" INTEGER NOT NULL,
ADD COLUMN     "updated_at" TIMESTAMP(3) NOT NULL;

-- AlterTable
ALTER TABLE "profiles" DROP COLUMN "createdAt",
DROP COLUMN "updatedAt",
DROP COLUMN "userId",
ADD COLUMN     "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN     "updated_at" TIMESTAMP(3) NOT NULL,
ADD COLUMN     "user_id" INTEGER NOT NULL;

-- AddForeignKey
ALTER TABLE "pages" ADD CONSTRAINT "pages_profile_id_fkey" FOREIGN KEY ("profile_id") REFERENCES "profiles"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
