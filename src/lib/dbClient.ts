import { PrismaClient } from "@prisma/client";

export function getDbClient(): PrismaClient {
  return new PrismaClient();
}