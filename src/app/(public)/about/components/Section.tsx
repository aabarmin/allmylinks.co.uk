import Typography from "@mui/material/Typography";
import { PageDivider } from "../../components/PageDivider";

export function Section({ title, children }: { title: string, children: React.ReactNode }) {
  return (
    <>
      <Typography variant="h5">{title}</Typography>
      <Typography>{children}</Typography>
      <PageDivider />
    </>
  );
}