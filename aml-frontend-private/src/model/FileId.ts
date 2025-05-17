export class FileResponse {
  storage: string;
  filePath: string;
  publicUrl: string;

  constructor(storage: string, filePath: string, publicUrl: string) {
    this.storage = storage
    this.filePath = filePath
    this.publicUrl = publicUrl
  }
}