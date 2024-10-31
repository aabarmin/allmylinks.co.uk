import { Container } from "@mui/material";

export function Subscribe() {
  return (
    <Container>
      <div>
        <h1>Subscribe</h1>
        <p>Subscribe to our newsletter</p>
        <form>
          <input type="email" placeholder="Email" />
          <button type="submit">Subscribe</button>
        </form>
      </div>
    </Container>
  );
}