import { Block } from "@prisma/client"
import { JsonValue } from "@prisma/client/runtime/library"

export type BlockResponse = {
  id: number,
  order: number,
  type: string
  props: JsonValue,
}

export function toBlockResponse(block: Block): BlockResponse {
  return {
    id: block.id,
    order: block.order,
    type: block.type,
    props: block.props,
  }
}