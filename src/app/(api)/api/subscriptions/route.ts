import { SubscribeRequest } from "@/lib/client/subscriptionActions";
import { getDbClient } from "@/lib/server/dbClient";
import { z } from "zod";
import { respondInvalidRequest } from "../restUtils";

export async function POST(request: Request) {
  const requestBody = (await request.json()) as SubscribeRequest;
  const scheme = z.object({
    email: z.string().email()
  })
  const validationResult = scheme.safeParse(requestBody);
  if (!validationResult.success) {
    return respondInvalidRequest(validationResult.error);
  }

  const existingEmail = await getDbClient().subscription.findUnique({
    where: { email: requestBody.email }
  })
  if (!existingEmail) {
    await getDbClient().subscription.create({
      data: { email: requestBody.email }
    });
  }

  return Response.json({ success: true });
}