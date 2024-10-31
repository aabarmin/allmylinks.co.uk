import { LandingArea } from "./components/LandingArea";
import { Navigation } from "./components/Navigation";
import { Subscribe } from "./components/Subscribe";
import { WhyUs } from "./components/WhyUs";

export function HomePage() {
  return (
    <>
      <Navigation />
      <LandingArea />
      <WhyUs />
      <Subscribe />
    </>
  );
}