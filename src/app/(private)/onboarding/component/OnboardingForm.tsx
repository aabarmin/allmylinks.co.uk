'use client';

import { createOrCompleteOnboarding } from "@/lib/client/onboardingActions";
import { hasProfileWithLink } from "@/lib/client/profileActions";
import { getCurrentUser } from "@/lib/client/userActions";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import { redirect } from "next/navigation";
import { SyntheticEvent } from "react";
import { useFormState, useFormStatus } from "react-dom";
import { z } from "zod";

type FormState = {
  errors?: {
    name?: string[],
    link?: string[]
  }
} | undefined;

async function completeOnboarding(state: FormState, formData: FormData) {
  const onboardingSpec = z.object({
    name: z.string().min(2, "Name must be at least 2 characters long"),
    link: z.string()
      .regex(/^[a-z0-9-]+$/, "Link must contain only lowercase letters, numbers and dashes")
      .min(2, "Link must be at least 2 characters long")
      .refine(async (value) => {
        if (value == '') {
          return true;
        }
        if (value.length < 2) {
          return true;
        }
        const hasProfile = await hasProfileWithLink(value)
        return hasProfile == false;
      }, "Link already in use")
  });

  const validationResult = await onboardingSpec.safeParseAsync({
    name: formData.get("name") as string,
    link: formData.get("link") as string
  })

  if (!validationResult.success) {
    return {
      errors: validationResult.error.flatten().fieldErrors
    }
  }

  const user = await getCurrentUser();
  if (!user) {
    throw new Error("User not found"); // should never happen
  }
  await createOrCompleteOnboarding({
    name: validationResult.data.name,
    link: validationResult.data.link,
    userId: user.id
  })

  redirect('/dashboard')
}

function SubmitButton() {
  const { pending } = useFormStatus()
  const handleClick = (e: SyntheticEvent) => {
    if (pending) {
      e.preventDefault();
    }
  };

  return (
    <Button
      type="submit"
      variant="contained"
      size="large"
      disabled={pending}
      onClick={handleClick}>
      Submit
    </Button>
  )
}

export function OnboardingForm() {
  const [state, dispatch] = useFormState(completeOnboarding, undefined)

  return (
    <form action={dispatch}>
      <Stack spacing={2}>
        <TextField
          name="name"
          label="Your name"
          required
          error={state?.errors?.name?.length ? true : false}
          helperText={state?.errors?.name?.join(", ")}
        />

        <TextField
          name="link"
          label="Short link"
          required
          error={state?.errors?.link?.length ? true : false}
          helperText={state?.errors?.link?.join(", ")}
        />

        <SubmitButton />
      </Stack>
    </form >
  );
}