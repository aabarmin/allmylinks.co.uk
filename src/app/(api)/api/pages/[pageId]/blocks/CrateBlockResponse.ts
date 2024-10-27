export class CreateBlockResponse {
  id: number;
  order: number;

  constructor(id: number, order: number) {
    this.id = id;
    this.order = order;
  }
}