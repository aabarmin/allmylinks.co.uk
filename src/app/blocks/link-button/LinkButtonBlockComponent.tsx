import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import { LinkButtonBlockProps } from "./LinkButtonBlock";

export function LinkButtonBlockComponent(props: LinkButtonBlockProps) {
  return (
    <Box sx={{
      p: 1
    }}>
      <Button
        size="large"
        variant="contained"
        color="primary"
        href={props.link}
        target="_blank"
        fullWidth
        rel="noreferrer"
      >
        {props.text}
      </Button>
    </Box>
  )
}