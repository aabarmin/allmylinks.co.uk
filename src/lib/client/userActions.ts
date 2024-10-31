import { CurrentUserResponse } from "@/app/(api)/api/users/current/CurrentUserResponse";

export async function getCurrentUser(): Promise<CurrentUserResponse | null> {
  const response = await fetch('/api/users/current')
  if (!response.ok) {
    return Promise.resolve(null)
  }

  return Promise.resolve(response.json());
}