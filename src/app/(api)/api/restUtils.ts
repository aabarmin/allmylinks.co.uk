import { ZodError } from "zod";

export function respondUnauthenticated(): Response {
  return Response.json({ error: "Not authenticated" }, { status: 401 });
}

export function respondInvalidRequest(error: ZodError): Response {
  return Response.json({ error: "Invalid request", details: error.flatten().fieldErrors }, { status: 400 });
}

export function respondNotFound(message?: string): Response {
  return Response.json({ error: message ? message : "Not found" }, { status: 404 });
}

export function respondServerError(message?: string): Response {
  return Response.json({ error: message ? message : "Internal server error" }, { status: 500 });
}