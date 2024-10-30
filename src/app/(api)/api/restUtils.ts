import { ZodError } from "zod";

export function respondUnauthenticated(): Response {
  return Response.json({ error: "Not authenticated" }, { status: 401 });
}

export function respondInvalidRequest(error: ZodError): Response {
  // todo, add errors to the response
  return Response.json({ error: "Invalid request" }, { status: 400 });
}