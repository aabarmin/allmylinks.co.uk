import Image from "next/image"

export function ServicePromoImage() {

  return (
    <Image
      alt="App preview"
      width={284}
      height={525}
      src="/home/preview.jpg" />
  )
}