import { User } from "@prisma/client";
import { Resend } from "resend";

const resend = new Resend(process.env.RESEND_API_KEY);

export enum EmailTemplate {
  REGISTERED = 'REGISTERED',
}

async function sendWelcomeEmail(user: User) {
  const { error } = await resend.emails.send({
    from: 'onboarding@resend.dev',
    to: 'abarmin@outlook.com',
    subject: 'Welcome to our service!',
    html: `<h1>Welcome, ${user.name}!</h1><p>Thank you for registering!</p>`
  })
  if (error) {
    console.error(error);
  }
}

export async function sendEmail(user: User, template: EmailTemplate) {
  // there might be a complicated java-like solution but I don't care at the moment
  if (template == EmailTemplate.REGISTERED) {
    sendWelcomeEmail(user);
  }
}