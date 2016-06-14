<?php

namespace loanapproval\Form;

use Silex\Application;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Form\Extension\Core\Type\TextType;

/**
 * Description of FormRegister.
 *
 * @author maxime
 */
class FormApproval
{
    public function createForm(Application $app)
    {
        $form = $app['form.factory']->createBuilder()
                ->add('name', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                ->add('amount', TextType::class, array(
                    'constraints' => array(new Assert\NotBlank()), ))
                ->getForm();

        return $form;
    }

}
