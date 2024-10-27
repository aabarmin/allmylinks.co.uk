-- CreateTable
CREATE TABLE "blocks" (
    "id" SERIAL NOT NULL,
    "page_id" INTEGER NOT NULL,
    "order" INTEGER NOT NULL,
    "block_type" TEXT NOT NULL,
    "props" JSONB NOT NULL,

    CONSTRAINT "blocks_pkey" PRIMARY KEY ("id")
);

-- AddForeignKey
ALTER TABLE "blocks" ADD CONSTRAINT "blocks_page_id_fkey" FOREIGN KEY ("page_id") REFERENCES "pages"("id") ON DELETE RESTRICT ON UPDATE CASCADE;
