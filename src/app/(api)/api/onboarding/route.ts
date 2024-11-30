import { AvatarBlock, AvatarBlockProps } from "@/app/blocks/avatar/AvatarBlock";
import { HeaderAlignment, HeaderBlock, HeaderBlockProps, HeaderLevel } from "@/app/blocks/header/HeaderBlock";
import { getDbClient } from "@/lib/server/dbClient";
import { getCurrentUser } from "@/lib/server/userActions";
import { Onboarding } from "@prisma/client";
import { z } from "zod";
import { respondInvalidRequest, respondUnauthenticated } from "../restUtils";
import { CreateOnboardingRequest } from "./CreateOnboardingRequest";

export async function POST(request: Request) {
  const user = await getCurrentUser();
  if (!user) {
    return respondUnauthenticated();
  }

  const requestScheme = z.object({
    name: z.string(),
    link: z.string(),
    userId: z.number()
  });

  const requestBody = await request.json();
  const validationResult = requestScheme.safeParse(requestBody);
  if (!validationResult.success) {
    return respondInvalidRequest(validationResult.error);
  }

  const onboardingRequest = validationResult.data as CreateOnboardingRequest;
  const updatedOnboarding = await getDbClient().$transaction(async (tx) => {
    const onboarding = await tx.onboarding.create({
      data: {
        userId: user.id,
        name: onboardingRequest.name,
        link: onboardingRequest.link,
        socialNetworks: []
      }
    });

    const avatarBlock = createAvatarBlock();
    const headerBlock = createHeaderBlock(onboarding);
    const profile = await tx.profile.create({
      data: {
        userId: user.id,
        link: onboardingRequest.link,
        active: true,
      }
    });
    const homePage = await tx.page.create({
      data: {
        profileId: profile.id,
        title: "Home",
        isHome: true,
      }
    });

    await tx.block.create({
      data: {
        pageId: homePage.id,
        order: avatarBlock.order,
        type: avatarBlock.type,
        props: avatarBlock.props as object
      }
    });
    await tx.block.create({
      data: {
        pageId: homePage.id,
        order: headerBlock.order,
        type: headerBlock.type,
        props: headerBlock.props as object
      }
    });
    const updatedOnboarding = await tx.onboarding.update({
      where: { id: onboarding.id },
      data: {
        isCompleted: true,
        updatedAt: new Date(),
      }
    });

    return updatedOnboarding;
  });

  return Response.json({ ok: updatedOnboarding.isCompleted }, { status: 201 });
}

function createAvatarBlock(): AvatarBlock {
  const props = new AvatarBlockProps();
  return new AvatarBlock(0, 1, props);
}

function createHeaderBlock(onboarding: Onboarding): HeaderBlock {
  const props = new HeaderBlockProps();
  props.alignment = HeaderAlignment.CENTER;
  props.level = HeaderLevel.H1;
  props.text = onboarding.name;
  return new HeaderBlock(0, 2, props);
}