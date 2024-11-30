import { getCurrentUser } from "@/lib/server/userActions";
import { Button, Card, CardActions, CardContent, Stack, TextField, Typography } from "@mui/material";
import { redirect } from "next/navigation";

export default async function EmailCard() {
  const user = await getCurrentUser();
  if (!user) {
    redirect("/login");
  }

  return (
    <Card>
      <CardContent>
        <Typography variant="h5">Email</Typography>

        <Stack spacing={2}>
          <TextField
            disabled
            value={user.email}
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