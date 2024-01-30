-- paid_amount ile ödenen miktar tutuldu.
ALTER TABLE installment
ADD COLUMN paid_amount DOUBLE PRECISION DEFAULT 0.0;