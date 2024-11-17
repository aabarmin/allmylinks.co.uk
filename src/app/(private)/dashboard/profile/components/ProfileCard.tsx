import { getDbClient } from "@/lib/server/dbClient";
import { getCurrentUser } from "@/lib/server/userActions";
import { Button, Card, CardActions, CardContent, Stack, TextField, Typography } from "@mui/material";
import { redirect } from "next/navigation";

export default async function ProfileCard() {
  const user = await getCurrentUser();
  if (!user) {
    redirect("/login");
  }
  const profile = await getDbClient().profile.findUnique({
    where: { userId: user.id }
  })
  if (!profile) {
    redirect('/login')
  }

  return (
    <Card>
      <CardContent>
        <Typography variant="h5">Profile</Typography>

        <Stack spacing={2}>
          <TextField
            disabled
            value={profile.link}
          />
        </Stack>
      </CardContent>
      <CardActions>
        <Button
          disabled
          size="small"
        >
          Edit
        </Button>
      </CardActions>
    </Card>
  );
}