'use client'

import { subscribe } from "@/lib/client/subscriptionActions";
import { ForwardToInbox } from "@mui/icons-material";
import { Container, FormHelperText, Stack } from "@mui/material";
import Button from "@mui/material/Button";
import Grid from "@mui/material/Grid2";
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
      sx={{
        width: '260px'
      }}
      onAbort={handleClick}
      disabled={pending}
      startIcon={<ForwardToInbox />}
      variant="contained"
      type="submit"
      color="primary">
      Subscribe
    </Button>
  )
}

export function Subscribe() {
  const [state, dispatch] = useFormState(submitForm, undefined);

  return (
    <Container>
      <Grid container>
        <Grid size={8} offset={2}>
          <Stack spacing={2}>
            <Typography
              variant="h2"
              textAlign="center">
              Subscribe
            </Typography>
            <Typography>
              Subscribe to our newsletter to stay up to date with our latest news
            </Typography>

            <form action={dispatch}>
              <Stack
                direction="row"
                spacing={2}>
                <TextField
                  required
                  type="email"
                  name="email"
                  label="Email"
                  variant="outlined"
                  fullWidth />
                {state?.errors.email && (
                  <FormHelperText>
                    {state.errors.email.join(", ")}
                  </FormHelperText>
                )}

                <SubscribeButton />
              </Stack>
            </form>
          </Stack>
        </Grid>
      </Grid>
    </Container>
  );
}