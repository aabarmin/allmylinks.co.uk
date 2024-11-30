import { getDbClient } from "@/lib/server/dbClient";
import { getCurrentUser } from "@/lib/server/userActions";

export type ByLinkResponse = {
  exists: boolean;
}

export async function GET(
  request: Request,
  { params }: { params: { link: string } }) {

  const user = await getCurrentUser();
  if (!user) {
    return Response.json({ error: "Not authenticated" }, { status: 401 });
  }

  const { link } = params;
  const profile = await getDbClient().profile.findUnique({
    where: { link }
  })

  return Response.json({
    exists: profile != null
  } as ByLinkResponse);
}