import { ZodError } from "zod";

export function respondUnauthenticated(): Response {
  return Response.json({ error: "Not authenticated" }, { status: 401 });
}

export function respondInvalidRequest(error: ZodError): Response {
  return Response.json({ error: "Invalid request", details: error.flatten().fieldErrors }, { status: 400 });
}