export default function MobileFrame({ blocks }: { blocks: React.ReactNode[] }) {
  return (
    <div style={{
      padding: 8,
      border: '5px solid #333',
      borderRadius: '3em',
      maxWidth: '530px',
      minWidth: '530px',
      minHeight: '954px'
    }}>
      {blocks}
    </div>
  );
}