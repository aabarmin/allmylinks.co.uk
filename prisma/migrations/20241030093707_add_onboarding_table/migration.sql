-- CreateTable
CREATE TABLE "onboarding" (
    "id" SERIAL NOT NULL,
    "user_id" INTEGER NOT NULL,
    "is_completed" BOOLEAN NOT NULL DEFAULT false,
    "link" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "social_networks" TEXT[],
    "created_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP(3),

    CONSTRAINT "onboarding_pkey" PRIMARY KEY ("id")
);

-- CreateIndex
CREATE UNIQUE INDEX "onboarding_user_id_key" ON "onboarding"("user_id");

-- CreateIndex
CREATE UNIQUE INDEX "onboarding_link_key" ON "onboarding"("link");
