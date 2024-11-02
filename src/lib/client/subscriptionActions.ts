export type SubscribeRequest = {
  email: string;
}

export async function subscribe(request: SubscribeRequest) {
  const result = await fetch("/api/subscriptions", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(request)
  });

  return result.json();
}