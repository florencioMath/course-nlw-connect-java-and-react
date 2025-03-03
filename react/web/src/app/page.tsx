import { Button } from '@/components/button';
import { IconButton } from '@/components/icon-button';
import { ArrowRightCircle, Copy } from 'lucide-react';

export default function Home() {
  return (
    <main>
      <Button>
        Enviar
        <ArrowRightCircle />
      </Button>

      <IconButton>
        <Copy />
      </IconButton>
    </main>
  );
}
