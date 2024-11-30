import { getDbClient } from "@/lib/server/dbClient";
import { getCurrentUser } from "@/lib/server/userActions";
import { Button, Card, CardContent, Stack, Typography } from "@mui/material";
import { redirect } from "next/navigation";

async function deactivateAccount() {
  'use server';

  const user = await getCurrentUser();
  if (!user) {
    redirect("/login");
  }
  await getDbClient().profile.update({
    data: { active: false },
    where: { userId: user.id },
  });
}

export default async function DangerCard() {
  const user = await getCurrentUser();
  if (!user) {
    redirect("/login");
  }
  const profile = await getDbClient().profile.findUnique({
    where: { userId: user.id },
  });
  if (!profile) {
    redirect('/login')
  }

  return (
    <Card>
      <CardContent>
        <Typography variant="h5">
          Danger Zone
        </Typography>

        <Stack spacing={2}>
          <form action={deactivateAccount}>
            <Button
              fullWidth
              type="submit"
              size="large"
              disabled={!profile.active}
              variant="contained"
              color="error"
            >
              Deactivate Account
            </Button>
          </form>
        </Stack>
      </CardContent>
    </Card>
  );
}