import { createClient, SupabaseClient } from '@supabase/supabase-js';

const supabaseUrl = process.env.SUPABASE_URL;
const supabaseKey = process.env.SUPABASE_KEY;

const supabase = createClient(supabaseUrl as string, supabaseKey as string);

export function getSupabaseClient(): SupabaseClient {
  return supabase;
}
