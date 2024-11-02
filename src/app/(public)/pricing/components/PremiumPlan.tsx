'use client'

import { subscribe } from "@/lib/client/subscriptionActions";
import { ForwardToInbox } from "@mui/icons-material";
import { Card, CardContent, FormHelperText } from "@mui/material";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Typography from "@mui/material/Typography";
import { redirect } from "next/navigation";
import { SyntheticEvent } from "react";
import { useFormState, useFormStatus } from "react-dom";
import { z } from "zod";

type FormState = {
  errors?: {
    email?: string[]
  }
} | undefined;

async function submitForm(state: FormState, formData: FormData) {
  const subscribeSpec = z.object({
    email: z.string().email("Invalid email")
  });

  const validationResult = await subscribeSpec.safeParseAsync({
    email: formData.get("email") as string
  })

  if (!validationResult.success) {
    return {
      errors: validationResult.error.flatten().fieldErrors
    }
  }

  await subscribe({
    email: validationResult.data.email
  })

  redirect('/subscribe/success');
}

function SubscribeButton() {
  const { pending } = useFormStatus();
  const handleClick = (e: SyntheticEvent) => {
    if (pending) {
      e.preventDefault();
    }
  }

  return (
    <Button
      fullWidth
      size="large"
      startIcon={<ForwardToInbox />}
      variant="contained"
      type="submit"
      onAbort={handleClick}
      disabled={pending}
      color="primary">
      Subscribe
    </Button>
  )
}

export function PremiumPlan() {
  const [state, dispatch] = useFormState(submitForm, undefined);

  return (
    <Card>
      <CardContent>
        <Typography variant="h4">Premium</Typography>
        <Typography variant="h6">
          Premium features coming soon. Stay tuned!
        </Typography>

        <form action={dispatch}>
          <TextField
            label="Email"
            name="email"
            fullWidth
            type="email"
            variant="outlined" />

          {state?.errors.email && (
            <FormHelperText>
              {state.errors.email.join(", ")}
            </FormHelperText>
          )}

          <SubscribeButton />
        </form>
      </CardContent>
    </Card>
  )
}