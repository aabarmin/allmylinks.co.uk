-- drop useless index
drop index if exists uploaded_files_bucket_file_path_key;

-- drop useless column
ALTER TABLE
  "uploaded_files" DROP if exists "bucket";