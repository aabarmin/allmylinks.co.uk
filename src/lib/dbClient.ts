import { PrismaClient } from "@prisma/client";

export function getDb(): PrismaClient {
  return new PrismaClient();
}