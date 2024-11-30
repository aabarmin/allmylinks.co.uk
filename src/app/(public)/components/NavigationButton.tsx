import Button from "@mui/material/Button"

export type ButtonProps = {
  startIcon?: React.ReactNode,
  children: React.ReactNode
}

export function NavigationButton({ children, startIcon }: ButtonProps) {
  return (
    <Button
      variant="text"
      startIcon={startIcon}
      sx={{
        color: 'white',
        textTransform: 'uppercase',
        '&:hover': {
          backgroundColor: 'transparent',
        },
      }}>
      {children}
    </Button>
  )
}