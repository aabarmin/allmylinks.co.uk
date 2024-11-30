import { getCurrentUser } from "@/lib/server/userActions";
import { respondUnauthenticated } from "../../restUtils";
import { CurrentUserResponse } from "./CurrentUserResponse";

export async function GET() {
  const user = await getCurrentUser();
  if (!user) {
    return respondUnauthenticated();
  }
  return Response.json({
    id: user.id,
  } as CurrentUserResponse);
}