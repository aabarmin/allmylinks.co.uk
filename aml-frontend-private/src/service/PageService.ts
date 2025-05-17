import axios from "axios";
import { plainToInstance } from "class-transformer";
import { PageModel } from "../model/PageModel";

export function getPage(pageId: number): Promise<PageModel> {
  return new Promise(resolve => {
    axios
      .get(`/private/api/pages/${pageId}`)
      .then(response => {
        const data = response.data as Record<string, unknown>
        const model = plainToInstance(PageModel, data)
        resolve(model)
      })
  });
}