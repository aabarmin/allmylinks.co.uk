import { ByLinkResponse } from "@/app/(api)/api/profiles/by-link/[link]/route";

export async function hasProfileWithLink(link: String): Promise<boolean> {
  const url = `/api/profiles/by-link/${link}`;
  const response = await fetch(url);
  const data = await response.json() as ByLinkResponse;

  return Promise.resolve(data.exists);
}