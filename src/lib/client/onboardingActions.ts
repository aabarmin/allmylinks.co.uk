import { CreateOnboardingRequest } from "@/app/(api)/api/onboarding/CreateOnboardingRequest";

export async function createOrCompleteOnboarding(request: CreateOnboardingRequest): Promise<boolean> {
  const response = await fetch('/api/onboarding', {
    method: 'POST',
    body: JSON.stringify(request)
  });

  return response.json();
}