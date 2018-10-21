import { ChatterModule } from './chatter.module';

describe('ChatterModule', () => {
  let chatterModule: ChatterModule;

  beforeEach(() => {
    chatterModule = new ChatterModule();
  });

  it('should create an instance', () => {
    expect(chatterModule).toBeTruthy();
  });
});
