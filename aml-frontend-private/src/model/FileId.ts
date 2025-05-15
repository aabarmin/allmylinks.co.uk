export class FileId {
  storage: string; 
  filePath: string;

  constructor(storage: string, filePath: string) {
    this.storage = storage;
    this.filePath = filePath;
  }
}